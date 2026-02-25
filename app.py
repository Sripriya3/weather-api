from flask import Flask, jsonify, request
from flask_cors import CORS
import pandas as pd
import os

app = Flask(__name__)
CORS(app)

CSV_PATH = os.path.join(os.path.dirname(__file__), 'testset.csv')

def load_data():
    df = pd.read_csv(CSV_PATH)
    df.columns = df.columns.str.strip()
    return df

@app.route('/api/weather', methods=['GET'])
def get_weather():
    df = load_data()
    page = int(request.args.get('page', 1))
    limit = int(request.args.get('limit', 50))
    
    start = (page - 1) * limit
    end = start + limit
    
    data = df[start:end].to_dict('records')
    
    return jsonify({
        'data': data,
        'total': len(df),
        'page': page,
        'limit': limit
    })

@app.route('/api/weather/stats', methods=['GET'])
def get_stats():
    df = load_data()
    
    stats = {
        'total_records': len(df),
        'avg_temp': df['_tempm'].mean(),
        'avg_humidity': df['_hum'].mean(),
        'avg_pressure': df[df['_pressurem'] != -9999]['_pressurem'].mean(),
        'conditions': df['_conds'].value_counts().to_dict()
    }
    
    return jsonify(stats)

@app.route('/api/weather/search', methods=['GET'])
def search_weather():
    df = load_data()
    condition = request.args.get('condition')
    
    if condition:
        df = df[df['_conds'].str.contains(condition, case=False, na=False)]
    
    return jsonify({
        'data': df.to_dict('records'),
        'count': len(df)
    })

if __name__ == '__main__':
    app.run(debug=True, port=5000)
